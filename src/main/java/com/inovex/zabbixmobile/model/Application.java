/*
This file is part of ZAX.

	ZAX is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	ZAX is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with ZAX.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.inovex.zabbixmobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "applications")
public class Application implements Comparable<Application> {

	private static final String INDEX_APPLICATION_SERVER = "application_server_idx";

	public static final String COLUMN_ID = "id";
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private long id;
	public static final String COLUMN_APPLICATIONID = "applicationid";
	@DatabaseField(uniqueIndexName = INDEX_APPLICATION_SERVER, columnName = COLUMN_APPLICATIONID)
	private long applicationId;
	public static final String COLUMN_HOSTID = "hostid";
	@DatabaseField(columnName = COLUMN_HOSTID, foreign = true, foreignAutoRefresh = true, index = true)
	Host host;
	public static final String COLUMN_NAME = "name";
	@DatabaseField(columnName = COLUMN_NAME)
	private String name;
	public static final String COLUMN_SERVER = "server";
	@DatabaseField(uniqueIndexName = INDEX_APPLICATION_SERVER, columnName = COLUMN_SERVER, foreign = true, foreignAutoRefresh = true)
	private ZabbixServer server;

	public Application() {

	}

	public long getApplicationId() {
		return applicationId;
	}

	public Host getHost() {
		return host;
	}

	public String getName() {
		return name;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZabbixServer getServer() {
		return server;
	}

	public void setServer(ZabbixServer server) {
		this.server = server;
	}

	@Override
	public String toString() {
		return getApplicationId() + " " + getName() + "(Host: " + host.getName() + ") [Server: " + server.getName() +"]";
	}

	@Override
	public int compareTo(Application another) {
		return name.compareTo(another.getName());
	}
}
