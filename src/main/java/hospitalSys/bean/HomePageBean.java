package hospitalSys.bean;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;
@Data
public class HomePageBean {
	// 文書id
	private Integer documentId;
	// 患者id
	private Integer kanjaId;
	//文書種類
	private String document;
	//紹介先病院
	private String referralHospitalInfo;
	//紹介元病院
	private String formerHospitalInfo;
	//処理状態
	private String flag;
	//時間
	private Timestamp createTime;
	//患者名前
	private String name;
}
