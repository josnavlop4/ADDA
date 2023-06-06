package _datos;

public record Cliente(Integer id, Double beneficio) {
	public static Cliente ofFormat(String[] v) {
		return new Cliente(Integer.parseInt(v[0]), Double.parseDouble(v[1]));
	}
}
