/*
 * Copyright 2017 FOLIO Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.folio_sec.example.domain.simplebank;

import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.Attribute;
import com.gs.fw.common.mithra.bulkloader.BulkLoader;
import com.gs.fw.common.mithra.bulkloader.BulkLoaderException;
import com.gs.fw.common.mithra.cache.*;
import com.gs.fw.common.mithra.connectionmanager.ConnectionManagerWrapper;
import com.gs.fw.common.mithra.connectionmanager.SchemaManager;
import com.gs.fw.common.mithra.connectionmanager.SourcelessConnectionManager;
import com.gs.fw.common.mithra.connectionmanager.TablePartitionManager;
import com.gs.fw.common.mithra.database.MithraAbstractTransactionalDatabaseObject;
import com.gs.fw.common.mithra.databasetype.DatabaseType;
import com.gs.fw.common.mithra.finder.MapperStackImpl;
import com.gs.fw.common.mithra.finder.RelatedFinder;
import com.gs.fw.common.mithra.finder.SqlQuery;
import com.gs.fw.common.mithra.util.MithraConfigurationManager;

import java.io.IOException;
import java.io.ObjectInput;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
* This file was automatically generated using Mithra 16.3.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public abstract class CustomerDatabaseObjectAbstract extends MithraAbstractTransactionalDatabaseObject implements MithraTransactionalDatabaseObject, MithraObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "FIRST_NAME,LAST_NAME,COUNTRY";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "t0.FIRST_NAME,t0.LAST_NAME,t0.COUNTRY";
	private static final String PK_WITH_ALIAS = "t0.CUSTOMER_ID = ?";
	private static final String PK_INDEX_COLS = "CUSTOMER_ID";
	protected CustomerDatabaseObjectAbstract()
	{
		super("Customer", "com.folio_sec.example.domain.simplebank.CustomerFinder",
			4, 4,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			false, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return CustomerFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return CustomerFinder.getFinderInstance();
	}

	public static CustomerData allocateOnHeapData()
	{
		return new CustomerData();
	}

	public static CustomerData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new CustomerData();
		data.zDeserializeFullData(in);
		return data;
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		CustomerData data = new CustomerData();
		data.zDeserializePrimaryKey(in);
		return this.createObject(data);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullNonDatedTransactionalCache(CustomerFinder.getPrimaryKeyAttributes(), this, CustomerFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullNonDatedCache(CustomerFinder.getPrimaryKeyAttributes(), this, CustomerFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialNonDatedTransactionalCache(CustomerFinder.getPrimaryKeyAttributes(), this, CustomerFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialNonDatedCache(CustomerFinder.getPrimaryKeyAttributes(), this, CustomerFinder.getImmutableAttributes(), new NonTransactionalUnderlyingObjectGetter(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			CustomerFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			CustomerFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			Customer.zConfigFullTx();
		}
		else
		{
			Customer.zConfigNonTx();
		}
	}

	public List getSimulatedSequenceInitValues()
	{
		ArrayList simulatedSequenceInitValues = new ArrayList(1);
		Attribute[] primaryKeyAttributes = CustomerFinder.getPrimaryKeyAttributes();
		SimulatedSequenceInitValues initValues = null;
		return simulatedSequenceInitValues;
	}

	public Object getSourceAttributeValueForSelectedObjectGeneric(SqlQuery query, int queryNumber)
	{
		return null;
	}

	public Object getSourceAttributeValueFromObjectGeneric(MithraDataObject object)
	{
		return null;
	}

	public Object getSourceAttributeValueGeneric(SqlQuery query, MapperStackImpl mapperStack, int queryNumber)
	{
		return null;
	}

	public String getDatabaseIdentifierGenericSource (Object source)
	{
		return connectionManager.getDatabaseIdentifier();
	}

	public DatabaseType getDatabaseTypeGenericSource(Object source)
	{
		return connectionManager.getDatabaseType();
	}

	public TimeZone getDatabaseTimeZoneGenericSource(Object source)
	{
		return getDatabaseTimeZone();
	}

	public Connection getConnectionGenericSource(Object source)
	{
		return connectionManagerWrapper.getConnection();
	}

	public BulkLoader createBulkLoaderGenericSource(Object source) throws BulkLoaderException 
	{
		return connectionManager.createBulkLoader();
	}

	public MithraDataObject inflateDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateCustomerData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkCustomerData(1, (CustomerData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflateCustomerPkData(rs, dt);
	}

	public String getSchemaGenericSource(Object source)
	{
		if (this.schemaManager != null)
		{
			return this.schemaManager.getSchema(this.getDefaultSchema());
		}

		return this.getDefaultSchema();
	}

	public String getTableNameGenericSource(Object source) throws MithraDatabaseException
	{
		return getCustomerTableName();
	}

	public String getCustomerTableName() throws MithraDatabaseException
	{
		if (this.tablePartitionManager != null)
		{
			return this.tablePartitionManager.getTableName(this.getDefaultTableName());
		}

		return this.getDefaultTableName();
	}

	public void setPrimaryKeyAttributes(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		CustomerData data = (CustomerData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getCustomerId());
	}

	public int setPrimaryKeyAttributesWithoutOptimistic(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		this.setPrimaryKeyAttributes(stm, pos, dataObj, databaseTimeZone, dt);
		return -1;
	}

	public String getPrimaryKeyWhereSql()
	{
		return "CUSTOMER_ID = ?";
	}

	public String getPrimaryKeyWhereSqlWithNullableAttribute(MithraDataObject dataObj)
	{
		return "";
	}

	public String getPrimaryKeyWhereSqlWithNullableAttributeWithDefaultAlias(MithraDataObject dataObj)
	{
		return "";
	}

	public String getColumnListWithPk(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.CUSTOMER_ID,t0.FIRST_NAME,t0.LAST_NAME,t0.COUNTRY";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*4);
		result.append(databaseAlias).append(".").append("CUSTOMER_ID");
		result.append(",").append(databaseAlias).append(".").append("FIRST_NAME");
		result.append(",").append(databaseAlias).append(".").append("LAST_NAME");
		result.append(",").append(databaseAlias).append(".").append("COUNTRY");
		return result.toString();
	}

	public Object getConnectionManager()
	{
		return connectionManager;
	}

	public void setConnectionManager(Object connectionManager, ConnectionManagerWrapper wrapper)
	{
		this.connectionManager = (SourcelessConnectionManager)connectionManager;
		this.connectionManagerWrapper = wrapper;
	}

	public CustomerData inflateCustomerData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		CustomerData data = inflateCustomerPkData(rs, dt);
		inflateNonPkCustomerData(2, data, rs, dt);
		return data;
	}

	public CustomerData inflateCustomerPkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		CustomerData _data = new CustomerData();
		int _pos = 1;
		_data.setCustomerId(_rs.getInt(_pos++));
		checkNullPrimitive(_rs, _data, "customerId");
		return _data;
	}

	public void inflateNonPkCustomerData(int _pos, CustomerData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			CustomerData _data = _datax;
			_data.setFirstName(trimString(_rs.getString(_pos++)));
			_data.setLastName(trimString(_rs.getString(_pos++)));
			_data.setCountry(trimString(_rs.getString(_pos++)));
		}
	}

	public DatabaseType getDatabaseType()
	{
		return connectionManager.getDatabaseType();
	}

	public TimeZone getDatabaseTimeZone()
	{
		return connectionManager.getDatabaseTimeZone();
	}

	protected String getSchema()
	{
		return this.getSchemaGenericSource(null);
	}

	public void setSchemaManager(Object schemaManager)
	{
		if( schemaManager instanceof SchemaManager )
		{
			this.schemaManager = (SchemaManager) schemaManager;
		}
		else
		{
			throw new IllegalArgumentException( "Schema manager class " + schemaManager.getClass().getName()
			+ " does not implement SchemaManager.class" );
		}
	}

	public void setTablePartitionManager(Object tablePartitionManager)
	{
		if( tablePartitionManager instanceof TablePartitionManager )
		{
			this.tablePartitionManager = (TablePartitionManager) tablePartitionManager;
		}
		else
		{
			throw new IllegalArgumentException( "Table partition manager class " + tablePartitionManager.getClass().getName()
			+ " does not implement TablePartitionManager.class" );
		}
	}

	public String getTableName()
	{
		return this.getDefaultTableName();
	}

	public String getDefaultTableName()
	{
		return "CUSTOMER";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		CustomerData data = (CustomerData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setInt(pos++, data.getCustomerId());
		if(data.isFirstNameNull())
		{
			throwNullAttribute("firstName");
		}

		stm.setString(pos++, data.getFirstName());
		if(data.isLastNameNull())
		{
			throwNullAttribute("lastName");
		}

		stm.setString(pos++, data.getLastName());
		if(data.isCountryNull())
		{
			throwNullAttribute("country");
		}

		stm.setString(pos++, data.getCountry());
	}

	public String getInsertFields()
	{
		return "CUSTOMER_ID,FIRST_NAME,LAST_NAME,COUNTRY";
	}

	public String getInsertQuestionMarks()
	{
		return "?,?,?,?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "";
	}

	public MithraObject createObject(MithraDataObject newData)
	{
		Customer newObject = new Customer();
		newObject.zSetFromCustomerData((CustomerData) newData);
		return newObject;
	}

	public String getPkColumnList(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.CUSTOMER_ID";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*4);
		result.append(databaseAlias);
		result.append(".");
		result.append("CUSTOMER_ID");
		return result.toString();
	}
}
