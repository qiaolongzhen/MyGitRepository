package cn.ithema.domain;

/**
 * 封装slq语句和本次sql语句返回数据封装类型;
 * 
 * @author qiaolongzhen
 *
 */
public class Mapper {

	private String sql;
	private String resultType;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@Override
	public String toString() {
		return "Mapper [sql=" + sql + ", resultType=" + resultType + "]";
	}

}
