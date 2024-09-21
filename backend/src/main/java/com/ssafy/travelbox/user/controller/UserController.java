package com.ssafy.travelbox.user.controller;

import com.ssafy.travelbox.user.model.dto.UserDto;
import com.ssafy.travelbox.user.model.service.UserService;
import com.ssafy.travelbox.util.JWTUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "회원 인증 컨트롤러", description = "로그인 로그아웃, 토큰처리등 회원의 인증관련 처리하는 클래스.")
@Slf4j
public class UserController {

	private final UserService userService;
	private final JWTUtil jwtUtil;


	public UserController(UserService userService, JWTUtil jwtUtil) {
		super();
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		log.debug("login user : {}");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.debug(userDto.toString());

			String password = userService.getPassword(userDto.getId());
			Argon2PasswordEncoder arg2SpringSecurity = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);

			if(arg2SpringSecurity.matches(userDto.getPassword(), password)){

				String accessToken = jwtUtil.createAccessToken(userDto.getId());
				String refreshToken = jwtUtil.createRefreshToken(userDto.getId());

				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

				//				발급받은 refresh token 을 DB에 저장.
				userService.saveRefreshToken(userDto.getId(), refreshToken);

				//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;

			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @Parameter(description = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userId);
				resultMap.put("userInfo", userDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
	@GetMapping("/logout/{userId}")
	@Hidden
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, userDto : {}", token, userDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getId()))) {
				String accessToken = jwtUtil.createAccessToken(userDto.getId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}



	@Operation(summary = "회원가입", description = "사용자 정보를 받아 회원가입")
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signup(
			@RequestBody @Parameter(description = "회원가입시 필요한 정보", required = true) UserDto userDto) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {

			log.debug(userDto.toString());
			boolean checkId = userService.existsById((userDto.getId()));
			log.debug("id:", checkId);
			// 회원가입할 유저 정보
			if(!checkId) {
				Argon2PasswordEncoder arg2SpringSecurity = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
				String hashedPassword = arg2SpringSecurity.encode(userDto.getPassword());

				userDto.setPassword(hashedPassword);
				userService.signUp(userDto);
				resultMap.put("message", "회원가입 성공");
				resultMap.put("userInfo", userDto);
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("회원가입 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "회원정보수정", description = "사용자 정보를 받아 회원정보수정")
	@PostMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(
			@RequestBody @Parameter(description = "수정할 회원 정보", required = true) UserDto userDto) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.debug(userDto.toString());

			boolean checkId = userService.existsById((userDto.getId()));

			// 회원가입할 유저 정보
			if(checkId) {
				userService.modify(userDto);
				resultMap.put("message", "업데이트 성공!");
				resultMap.put("userInfo", userDto);
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "회원 정보가 존재하지 않습니다.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("회원 정보 수정 에러발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "회원탈퇴")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> delete(
			@PathVariable @Parameter(description = "탈퇴할 회원 아이디", required = true) String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			boolean checkId = userService.existsById(userId);

			// 회원가입할 유저 정보
			if(checkId) {
				userService.delete(userId);
				resultMap.put("message", "회원탈퇴 성공!");
				resultMap.put("userId", userId);
				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "회원 정보가 존재하지 않습니다.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.debug("회원탈퇴 에러발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}
}
