public class NBody {

	private static String imageToDraw = "images/starfield.jpg";

	public static double readRadius(String s) {
		In in = new In(s);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String s) {
		
		In in = new In(s);
		int N = in.readInt();
		Planet[] b = new Planet[N];
		double radius = in.readDouble();

		for (int i = 0; i < N; i++) {
				b[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
					in.readDouble(), in.readDouble(), in.readString());
		}
		return b;
	}


	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String s = args[2];
		double r = readRadius(s);
		Planet[] b = readPlanets(s);

		int N = b.length;
		double t = 0;
		double[] xForces = new double[N];
		double[] yForces = new double[N];

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-r, r);


		for (; t <= T; t = t + dt) {

			for (int i = 0; i < N; i++) {
				xForces[i] = b[i].calcNetForceExertedByX(b);
				yForces[i] = b[i].calcNetForceExertedByY(b);
			}

			// StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);

			for (int i = 0; i < N; i++) {
				b[i].update(dt, xForces[i], yForces[i]);
				b[i].draw();
			}

			StdDraw.show();

			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", b.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < b.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  b[i].xxPos, b[i].yyPos, b[i].xxVel,
                  b[i].yyVel, b[i].mass, b[i].imgFileName);   
		}

	}




}