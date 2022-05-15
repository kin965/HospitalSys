package hospitalSys.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class KanjaInfoBean {

	private Integer kanjaId;

	private Integer uketoriId;

	private String name;

	private String kana;

	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	private String gender;

	private Integer postalCode;

	private String address;

	private String homeTel;

	private String workTel;

	private Timestamp createTime;

}
