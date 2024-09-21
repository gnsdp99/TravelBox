import axios from "axios";

const { VITE_VUE_API_URL, VITE_ELECTRIC_CHARGING_STATION_URL } = import.meta.env;

function stationAxios() {
	const instance = axios.create({
		baseURL: VITE_ELECTRIC_CHARGING_STATION_URL,
		headers: {
			"Content-Type": "application/json;charset=utf-8",
		},
	});
	return instance;
}

function localAxios() {
	const instance = axios.create({
		baseURL: VITE_VUE_API_URL,
	});

	instance.defaults.headers.common["Authorization"] = "";
	instance.defaults.headers.post["Content-Type"] = "application/json";
	instance.defaults.headers.put["Content-Type"] = "application/json";

	return instance;
}

export { localAxios, stationAxios };
