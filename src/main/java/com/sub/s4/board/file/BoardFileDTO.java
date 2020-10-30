package com.sub.s4.board.file;

public class BoardFileDTO {
	
	private long fileNum; //여기 정보담에서 매퍼 만들어서 거기로 보냄
	private long num;
	private String fileName;
	private String oriName;
	
	public long getFileNum() {
		return fileNum;
	}
	public void setFileNum(long fileNum) {
		this.fileNum = fileNum;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	

}
