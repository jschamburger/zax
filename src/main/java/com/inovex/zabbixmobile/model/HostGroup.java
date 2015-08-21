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

@DatabaseTable(tableName = "hostgroups")
public class HostGroup implements Comparable<HostGroup> {

	public static final long GROUP_ID_ALL = -1;

	private static final String INDEX_GROUP_SERVER = "group_server_idx";

	public static final String COLUMN_ID = "id";
	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private long id;

	public static final String COLUMN_GROUPID = "groupid";
	@DatabaseField(uniqueIndexName = INDEX_GROUP_SERVER, columnName = COLUMN_GROUPID)
	private long groupId;

	public static final String COLUMN_NAME = "name";
	@DatabaseField(columnName = COLUMN_NAME)
	private String name;

	/** zabbix server */
	public static final String COLUMN_SERVER = "server";
	@DatabaseField(uniqueIndexName = INDEX_GROUP_SERVER, foreign = true, foreignAutoRefresh = true, columnName = COLUMN_SERVER)
	private ZabbixServer server;

	public HostGroup() {

	}

	public HostGroup(long groupId, String name) {
		this.groupId = groupId;
		this.name = name;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
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
		return groupId + " " + name;
	}

	@Override
	public int compareTo(HostGroup another) {
		if (groupId > another.getGroupId())
			return 1;
		if (groupId < another.getGroupId())
			return -1;
		return 0;
	}

}
