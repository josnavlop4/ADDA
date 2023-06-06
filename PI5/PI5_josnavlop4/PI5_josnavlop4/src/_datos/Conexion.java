package _datos;

public record Conexion(Integer cliente1, Integer cliente2, Double kms) {
	public static Conexion ofFormat(String[] v) {
		return new Conexion(Integer.parseInt(v[0]), Integer.parseInt(v[1]),Double.parseDouble(v[2]));
	}
}
