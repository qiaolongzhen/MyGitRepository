package cn.ithema.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装数据库基本信息&Mapper信息;
 * 
 * @author qiaolongzhen
 *
 */
public class Configuation {

	private String driver;
	private String url;
	private String username;
	private String password;
	private Map<String, Mapper> mappers = new HashMap<>(0);

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Mapper> getMappers() {
		return mappers;
	}

	public void setMappers(Map<String, Mapper> mappers) {
		this.mappers = mappers;
	}

	@Override
	public String toString() {
		return "Configuation [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", mappers=" + mappers + "]";
	}

}
