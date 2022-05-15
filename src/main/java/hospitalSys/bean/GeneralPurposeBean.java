package hospitalSys.bean;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

//定型　汎用紹介状
@Data
public class GeneralPurposeBean {
	private Integer kanjaId;
	private Integer uketoriId;
	private  String   name;
	private  String kana;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private  String gender;
	private Integer postalCode;
	private  String address;
	private  String homeTel;
	private String workTel;
	private Timestamp createTime;

	private Integer documentId;
	private String document;
    private String hospitalName;
    private String section;
    private String hospitalAddress;
    private String hospitalPostalCode;
    private String hospitalTel;
    private String userName;

    private Date inquiryTime;

    private String referralHospitalInfo;

    private Integer illnessId;
    // 傷病名・主訴
 	private String complaints;
 	// 紹介目的
 	private String purpose;
 	// 既往歴
 	private String medicalHistory;
 	// 家族歴
 	private String familyHistory;
 	// 現疾患（診断内容）
 	private String diagnosis;
 	// 治療経過
 	private String treatmentCourse;
 	// 現在の処方
 	private String prescription;
 	// 身体所見
 	private String iiView;
 	// 生活習慣
 	private String habit;
 	// 連絡事項
 	private String announcements;

}
