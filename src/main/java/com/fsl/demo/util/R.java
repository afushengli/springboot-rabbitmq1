package com.fsl.demo.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @description : 通用返回结果
 * @author      : Hanqing.Chen
 * @date        : 2019/11/5 14:07
 * @version     : V1.0.0
 * Copyright(C)易比得信息服务(北京)有限公司-版权所有
 */
public class R extends HashMap<String, Object> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", ResponseCode.SUCCESS.getCode());
		put("msg", ResponseCode.SUCCESS.getMsg());
	}

	public static R error(ResponseCode responseCode) {
		R r = new R();
		r.put("code", responseCode.getCode());
		r.put("msg", responseCode.getMsg());
		return r;
	}

	public static R error(ResponseCode responseCode, String msg) {
		R r = new R();
		r.put("code", responseCode.getCode());
		r.put("msg", msg);
		return r;
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
