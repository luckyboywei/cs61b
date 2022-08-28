public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double g = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
				double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b) {
		double distance = 0;
		double tmp = (xxPos - b.xxPos) * (xxPos - b.xxPos) + (yyPos - b.yyPos) * (yyPos - b.yyPos);
		distance = Math.sqrt(tmp);
		return distance;
	}

	/** Calculate the force exerted by the other object.
	 *@author Wei ZHOU 
	 */
	public double calcForceExertedBy(Planet b) {
		double force = 0;
		double r = this.calcDistance(b);
		force = Planet.g * this.mass * b.mass / (r * r);
		return force;
	}

	public double calcForceExertedByX(Planet b) {
		double r = this.calcDistance(b);
		double dx = b.xxPos - this.xxPos;
		double forceExertedByX = 0;
		forceExertedByX = this.calcForceExertedBy(b) * dx / r;
		return forceExertedByX;
	}

	public double calcForceExertedByY(Planet b) {
		double forceExertedByY = 0;
		double r = this.calcDistance(b);
		double dy = b.yyPos - this.yyPos;
		forceExertedByY = this.calcForceExertedBy(b) * dy / r;
		return forceExertedByY;
	}

	public double calcNetForceExertedByX(Planet[] allbodys) {
		int len = allbodys.length;
		double xNetForce = 0;
		for (int i = 0; i < len; i++) {
			if (this.equals(allbodys[i])) {
				continue;
			}
			xNetForce = xNetForce + this.calcForceExertedByX(allbodys[i]);
		}
		return xNetForce;
	}

	public double calcNetForceExertedByY(Planet[] allbodys) {
		int len = allbodys.length;
		double yNetForce = 0;
		// for (int i = 0; i < len; i++) {
		// 	if (this.equals(allbodys[i])) {
		// 		continue;
		// 	}
		// 	yNetForce = yNetForce + this.calcForceExertedByY(allbodys[i]);
		// }

		for (Planet b : allbodys) {
			if (this.equals(b)) {
				continue;
			}
			yNetForce = yNetForce + this.calcForceExertedByY(b);
		}
		return yNetForce;

	}



	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel + aX * dt;
		yyVel = yyVel + aY * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	public void draw() {
		String imgToDraw = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imgToDraw);
	}

}