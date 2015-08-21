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

package com.inovex.zabbixmobile.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.inovex.zabbixmobile.R;
import com.inovex.zabbixmobile.model.Application;
import com.inovex.zabbixmobile.model.ApplicationItemRelation;
import com.inovex.zabbixmobile.model.Event;
import com.inovex.zabbixmobile.model.Host;
import com.inovex.zabbixmobile.model.HostGroup;
import com.inovex.zabbixmobile.model.HostHostGroupRelation;
import com.inovex.zabbixmobile.model.Item;
import com.inovex.zabbixmobile.model.Trigger;
import com.inovex.zabbixmobile.model.TriggerHostGroupRelation;
import com.inovex.zabbixmobile.model.TriggerSeverity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mock database helper which creates a database containing static data.
 * 
 */
public class MockDatabaseHelper extends DatabaseHelper {

	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "zabbixmobile_test.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = MockDatabaseHelper.class.getSimpleName();

	public MockDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		super.onCreate(db, connectionSource);
		try {
			Dao<Event, Long> eventDao = getDao(Event.class);
			Dao<Trigger, Long> triggerDao = getDao(Trigger.class);
			Event[] events = new Event[] {
					new Event(12345, 0, System.currentTimeMillis()
							- (3600 * 1000 * 12), 1, false),
					new Event(13467, 0, System.currentTimeMillis()
							- (3600 * 1000 * 10), 0, true),
					new Event(17231, 0, System.currentTimeMillis()
							- (3600 * 1000 * 7), 1, false),
					new Event(19865, 0, System.currentTimeMillis()
							- (3600 * 1000 * 5), 0, false),
					new Event(14562, 0, System.currentTimeMillis()
							- (3600 * 1000 * 9), 1, true),
					new Event(19872, 0, System.currentTimeMillis()
							- (3600 * 1000 * 4), 0, false),
					new Event(20616, 0, System.currentTimeMillis()
							- (3600 * 1000 * 3), 1, true),
					new Event(21576, 0, System.currentTimeMillis()
							- (3600 * 1000 * 2), 0, false),
					new Event(25821, 0, System.currentTimeMillis()
							- (3600 * 1000 * 0), 1, true),
					new Event(14529, 0, System.currentTimeMillis()
							- (3600 * 1000 * 8), 0, false) };

			Trigger[] triggers = new Trigger[] {
					new Trigger(14062, "Sample trigger #1", "{13513}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 12),
							TriggerSeverity.AVERAGE, 0, 1, "URL"),
					new Trigger(14063, "Sample trigger #2", "{1}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 10),
							TriggerSeverity.DISASTER, 0, 1, "URL"),
					new Trigger(14064, "Sample trigger #3", "{32415}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 7), TriggerSeverity.HIGH,
							0, 1, "URL"),
					new Trigger(14065, "Sample trigger #4", "{13518}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 5),
							TriggerSeverity.NOT_CLASSIFIED, 1, 1, "URL"),
					new Trigger(14066, "Sample trigger #5", "{12}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 9),
							TriggerSeverity.WARNING, 0, 1, "URL"),
					new Trigger(14067, "Sample trigger #6", "{13518}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 4),
							TriggerSeverity.AVERAGE, 1, 1, "URL"),
					new Trigger(14068, "Sample trigger #7", "{431}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 3), TriggerSeverity.HIGH,
							1, 1, "URL"),
					new Trigger(14069, "Sample trigger #8", "{13518}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 2),
							TriggerSeverity.INFORMATION, 0, 1, "URL"),
					new Trigger(14070, "Sample trigger #9", "{123}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 0), TriggerSeverity.HIGH,
							1, 1, "URL"),
					new Trigger(14071, "Sample trigger #10", "{13518}>0",
							"Comments...", System.currentTimeMillis()
									- (3600 * 1000 * 8),
							TriggerSeverity.INFORMATION, 0, 1, "URL")

			};
			int i = 0;
			for (Trigger t : triggers) {
				triggerDao.create(t);
				events[i].setTrigger(t);
				i++;
			}
			for (Event e : events) {
				eventDao.create(e);
			}

		} catch (SQLException e) {
			Log.e(TAG, e.toString());
		}
	}

	@Override
	public void insertEvents(Collection<Event> events) {
	}

	@Override
	public void insertTriggers(Collection<Trigger> triggers) {
	}

	@Override
	public void insertHosts(List<Host> hosts) {
	}

	@Override
	public void insertHostGroups(ArrayList<HostGroup> hostGroups) {
	}

	@Override
	public void insertApplications(Collection<Application> applications) {
	}

	@Override
	public void insertTriggerHostgroupRelations(
			List<TriggerHostGroupRelation> triggerHostGroupCollection) {
	}

	@Override
	public void insertHostHostgroupRelations(
			List<HostHostGroupRelation> hostHostGroupCollection) {
	}

	@Override
	public void insertApplicationItemRelations(
			List<ApplicationItemRelation> applicationItemRelations) {
	}

	@Override
	public void insertItems(List<Item> itemCollection) {
	}

	@Override
	public void clearEvents() {
	}

	@Override
	public void clearTriggers() {
	}

	@Override
	public void clearItems() {
	}

}
