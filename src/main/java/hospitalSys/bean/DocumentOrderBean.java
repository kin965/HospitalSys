package hospitalSys.bean;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class DocumentOrderBean {
	// 患者ID
	private Integer kanjaId;
	// 患者氏名
	private String name;
	// カタカナ氏名
	private String kana;
	// 誕生日
	private Date birthday;
	// 性別
	private String gender;
	// 郵便番号
	private String postalCode;
	// 住所
	private String address;
	// 家の電話番号
	private String homeTel;
	// 仕事先の電話番号
	private String workTel;
	// 病気情報ID
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
	// 病気情報作り時間
	private Timestamp iiCreateTime;
	// 文書情報ID
	private Integer documentId;
	// 文書種類
	private String document;
	// 紹介先病院 情報
	private String referralHospitalInfo;
	// 紹介元病院 情報
	private String formerHospitalInfo;
	// 処理状態
	private int flag;
	// 文書情報作る時間
	private Timestamp diCreateTime;

}

/*
 * comment on table illness_info is '病気情報'; comment on column
 * illness_info.illness_id is '病気情報id'; comment on column illness_info.kanja_id
 * is '患者id'; comment on column illness_info.complaints is '傷病名・主訴'; comment on
 * column illness_info.purpose is '紹介目的'; comment on column
 * illness_info.medical_history is '既往歴'; comment on column
 * illness_info.family_history is '家族歴'; comment on column
 * illness_info.diagnosis is '現疾患（診断内容）'; comment on column
 * illness_info.treatment_course is '治療経過'; comment on column
 * illness_info.prescription is '現在の処方'; comment on column illness_info.view is
 * '身体所見'; comment on column illness_info.habit is '生活習慣'; comment on column
 * illness_info.announcements is '連絡事項'; comment on column
 * illness_info.create_time is '創建時間';
 */
