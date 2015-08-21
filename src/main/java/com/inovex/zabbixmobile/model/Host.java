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

@DatabaseTable(tableName = "hosts")
public class Host implements Comparable<Host> {
	
	public static final int STATUS_MONITORED = 0;

	private static final String INDEX_HOST_SERVER = "host_server_idx";

	public static final String COLUMN_ID = "id";
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	long id;

	public static final String COLUMN_HOSTID = "hostid";
	@DatabaseField(uniqueIndexName = INDEX_HOST_SERVER, columnName = COLUMN_HOSTID)
	long hostId;

	public static final String COLUMN_HOST = "host";
	@DatabaseField(columnName = COLUMN_HOST)
	String name;
	
	public static final String COLUMN_STATUS = "status";
	@DatabaseField(columnName = COLUMN_STATUS)
	int status;

	public static final String COLUMN_SERVER = "server";
	@DatabaseField(uniqueIndexName = INDEX_HOST_SERVER, foreign = true, foreignAutoRefresh = true, columnName = COLUMN_SERVER)
	ZabbixServer server;

	HostGroup group;

	public Host() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHostId() {
		return hostId;
	}

	public void setHostId(long hostId) {
		this.hostId = hostId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HostGroup getGroup() {
		return group;
	}

	public void setGroup(HostGroup group) {
		this.group = group;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ZabbixServer getServer() {
		return server;
	}

	public void setServer(ZabbixServer server) {
		this.server = server;
	}

	@Override
	public int compareTo(Host another) {
		if (hostId == another.getHostId() && server.equals(another.getServer()))
			return 0;
		if (hostId > another.getHostId())
			return 1;
		return -1;
	}

	@Override
	public String toString() {
		return getHostId() + ": " + getName();
	}

}
