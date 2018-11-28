package test;


public class Orgs {
	public static void main(String[] args) {
		String ids = "1,2,3,4";
		String[] orgs = ids.split(",");
		for(String org : orgs){
			System.out.println(org);
		}
	}
}
