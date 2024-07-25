package com.test.sku.serialization;

import java.io.Serializable;

public class Emp implements Serializable{
	private int empno;
	private String ename;
    private int deptno;
    private int sal;
    
	public Emp() {
		super();
	}
	public Emp(int empno) {			//key = empno
		super();
		this.empno = empno;
	}
	public Emp(int empno, String ename, int deptno, int sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.deptno = deptno;
		this.sal = sal;
	}
	
	@Override
	public String toString() {
		String s = String.format("%d\t%s\t%d\t%d", empno,ename,deptno,sal);
		return s;
	}
	@Override
	public boolean equals(Object obj) {	//equals했을 때 같이써야한다 hashCode!!!!!시험에 나와
		Emp other = (Emp) obj;
		return this.getEmpno()==other.getEmpno();
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}

}
