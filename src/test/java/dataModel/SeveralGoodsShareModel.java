package dataModel;
/**
 * @author helen
 * @date 2018年9月25日
 */

/**
 * @Description:excel中的“SeveralGoodsShare”对象
 */
public class SeveralGoodsShareModel {
	private String testCaseNB;
	private String coupon;
	private String bean;
	private String g1c;
	private String g1b;
	private String g1m;
	private String g2c;
	private String g2b;
	private String g2m;
	private String g3c;
	private String g3b;
	private String g3m;
	private String g4c;
	private String g4b;
	private String g4m;
	private String g5c;
	private String g5b;
	private String g5m;
	private String g6c;
	private String g6b;
	private String g6m;
	private String g7c;
	private String g7b;
	private String g7m;
	private String g8c;
	private String g8b;
	private String g8m;
	private String g9c;
	private String g9b;
	private String g9m;

	public String getTestCaseNB() {
		return this.testCaseNB;
	}

	public void setTestCaseNB(String testcaseNB) {
		this.testCaseNB = testcaseNB;
	}

	public String getCoupon() {
		return this.coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getBean() {
		return this.bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}
	
	/*获取各商品的分摊优惠券
	 * @i  商品编号
	 * */
	public String getGoodsCoupon(int i) {
		String GC="";
		if (i==1) {
			GC = this.getG1c();
		}
		else if (i==2) {
			GC = this.getG2c();
		}
		else if (i==3) {
			GC= this.getG3c();
		}
		else if (i==4) {
			GC=this.getG4c();
		}
		else if (i==5) {
			GC= this.getG5c();
		}
		else if (i==6) {
			GC = this.getG6c();
		}
		else if (i==7) {
			GC = this.getG7c();
		}
		else if (i==8) {
			GC = this.getG8c();
		}
		else if (i==9) {
			GC = this.getG9c();
		}
		return GC;
	}
	
	/*获取各商品的分摊积分
	 * @i  商品编号
	 * */
	public String getGoodsBean(int i) {
		String GBean = "";
		if (i==1) {
			GBean = this.getG1b();
		}
		else if (i==2) {
			GBean = this.getG2b();
		}
		else if (i==3) {
			GBean = this.getG3b();
		}
		else if (i==4) {
			GBean = this.getG4b();
		}
		else if (i==5) {
			GBean = this.getG5b();
		}
		else if (i==6) {
			GBean = this.getG6b();
		}
		else if (i==7) {
			GBean = this.getG7b();
		}
		else if (i==8) {
			GBean = this.getG8b();
		}
		else if (i==9) {
			GBean = this.getG9b();
		}
		return GBean;
	}
	
	/*获取各商品的需要支付的现金
	 * @i  商品编号
	 * */
	public String getGoodsMoney(int i) {
		String gm = "";
		if (i==1) {
			gm = this.getG1m();
		}
		else if (i==2) {
			gm = this.getG2m();
		}
		else if (i==3) {
			gm = this.getG3m();
		}
		else if (i==4) {
			gm = this.getG4m();
		}
		else if (i==5) {
			gm = this.getG5m();
		}
		else if (i==6) {
			gm = this.getG6m();
		}
		else if (i==7) {
			gm = this.getG7m();
		}
		else if (i==8) {
			gm = this.getG8m();
		}
		else if (i==9) {
			gm = this.getG9m();
		}
		return gm;
	}

	public String getG1c() {
		return this.g1c;
	}

	public void setG1c(String g1c) {
		this.g1c = g1c;
	}

	public String getG1b() {
		return this.g1b;
	}

	public void setG1b(String g1b) {
		this.g1b = g1b;
	}

	public String getG1m() {
		return this.g1m;
	}

	public void setG1m(String g1m) {
		this.g1m = g1m;
	}

	public String getG2c() {
		return this.g2c;
	}

	public void setG2c(String g2c) {
		this.g2c = g2c;
	}

	public String getG2b() {
		return this.g2b;
	}

	public void setG2b(String g2b) {
		this.g2b = g2b;
	}

	public String getG2m() {
		return this.g2m;
	}

	public void setG2m(String g2m) {
		this.g2m = g2m;
	}

	public String getG3c() {
		return this.g3c;
	}

	public void setG3c(String g3c) {
		this.g3c = g3c;
	}

	public String getG3b() {
		return this.g3b;
	}

	public void setG3b(String g3b) {
		this.g3b = g3b;
	}

	public String getG3m() {
		return this.g3m;
	}

	public void setG3m(String g3m) {
		this.g3m = g3m;
	}

	public String getG4c() {
		return this.g4c;
	}

	public void setG4c(String g4c) {
		this.g4c = g4c;
	}

	public String getG4b() {
		return this.g4b;
	}

	public void setG4b(String g4b) {
		this.g4b = g4b;
	}

	public String getG4m() {
		return this.g4m;
	}

	public void setG4m(String g4m) {
		this.g4m = g4m;
	}

	public String getG5c() {
		return this.g5c;
	}

	public void setG5c(String g5c) {
		this.g5c = g5c;
	}

	public String getG5b() {
		return this.g5b;
	}

	public void setG5b(String g5b) {
		this.g5b = g5b;
	}

	public String getG5m() {
		return this.g5m;
	}

	public void setG5m(String g5m) {
		this.g5m = g5m;
	}

	public String getG6c() {
		return this.g6c;
	}

	public void setG6c(String g6c) {
		this.g6c = g6c;
	}

	public String getG6b() {
		return this.g6b;
	}

	public void setG6b(String g6b) {
		this.g6b = g6b;
	}

	public String getG6m() {
		return this.g6m;
	}

	public void setG6m(String g6m) {
		this.g6m = g6m;
	}

	public String getG7c() {
		return this.g7c;
	}

	public void setG7c(String g7c) {
		this.g7c = g7c;
	}

	public String getG7b() {
		return this.g7b;
	}

	public void setG7b(String g7b) {
		this.g7b = g7b;
	}

	public String getG7m() {
		return this.g7m;
	}

	public void setG7m(String g7m) {
		this.g7m = g7m;
	}

	public String getG8c() {
		return this.g8c;
	}

	public void setG8c(String g8c) {
		this.g8c = g8c;
	}

	public String getG8b() {
		return this.g8b;
	}

	public void setG8b(String g8b) {
		this.g8b = g8b;
	}

	public String getG8m() {
		return this.g8m;
	}

	public void setG8m(String g8m) {
		this.g8m = g8m;
	}

	public String getG9c() {
		return this.g9c;
	}

	public void setG9c(String g9c) {
		this.g9c = g9c;
	}

	public String getG9b() {
		return this.g9b;
	}

	public void setG9b(String g9b) {
		this.g9b = g9b;
	}

	public String getG9m() {
		return this.g9m;
	}

	public void setG9m(String g9m) {
		this.g9m = g9m;
	}

}
