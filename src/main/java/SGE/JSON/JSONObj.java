package SGE.JSON;

public interface JSONObj {
	public JSONObj getObject(String key);
	public double getDouble(String key);
	public String getString(String key);
	public boolean getBoolean(String key);
	public String toString();
	public String[] getKeys();
}
