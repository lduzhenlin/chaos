import request from '/@/utils/request';
import qs from 'qs';

// 登录方式
export enum LoginTypeEnum {
	PASSWORD,
	MOBILE,
	REGISTER,
	EXPIRE,
	QRCODE,
	FORGET,
}

// 登录错误信息
export enum LoginErrorEnum {
	CREDENTIALS_EXPIRED = 'credentials_expired', // 密码过期
}

/**
 * 登录
 * @param data
 */
export const login = (data: any) => {
	const { username, password } = data;
	return request({
		url: '/admin/login',
		method: 'post',
		data: qs.stringify({ username, password }),
		headers: {
			skipToken: true,
			skipTenant: true,
			'Content-Type': 'application/x-www-form-urlencoded',
			'Enc-Flag': 'false',
		},
	});
};

export const loginByMobile = (mobile: any, code: any) => {
	return request({
		url: '/admin/login/mobile',
		method: 'post',
		data: { mobile, code },
		headers: {
			skipToken: true,
			skipTenant: true,
		},
	});
};

export const sendMobileCode = (mobile: string) => {
	return request({
		url: '/admin/sysMessage/send/smsCode',
		method: 'get',
		params: { mobile },
	});
};

/**
 * 获取用户信息
 */
export const getUserInfo = () => {
	return request({
		url: '/admin/user/info',
		method: 'get',
	});
};

export const logout = () => {
	return request({
		url: '/admin/logout',
		method: 'delete',
	});
};
