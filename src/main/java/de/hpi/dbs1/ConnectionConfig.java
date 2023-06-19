package de.hpi.dbs1;

public interface ConnectionConfig {
	String getHost();
	int getPort();
	String getDatabase();
	String getUsername();
	String getPassword();
}
