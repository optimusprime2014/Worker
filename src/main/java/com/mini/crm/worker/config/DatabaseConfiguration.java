package com.mini.crm.worker.config;

import com.mini.crm.worker.model.ClassName;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    private static final String DB_LEVEL = "remote:localhost";

    private OrientDB orientDB;
    private ODatabaseSession dbSession;

    @Autowired
    WorkerConfiguration configuration;

    public void initDB() {
        //start-point init db
        orientDB = new OrientDB(DB_LEVEL, OrientDBConfig.defaultConfig());
        dbSession = orientDB.open("workero", "root", "2019");

        //create company vertex
        OClass company = dbSession.getClass(ClassName.V_COMPANY.toString());
        if (company == null) {
            company = dbSession.createVertexClass(ClassName.V_COMPANY.toString());
        }

        //create employee vertex
        OClass employee = dbSession.getClass(ClassName.V_EMPLOYEE.toString());
        if (employee == null) {
            employee = dbSession.createVertexClass(ClassName.V_EMPLOYEE.toString());
        }

        //create relation edge
        OClass relation = dbSession.getClass(ClassName.E_RELATION.toString());
        if (relation == null) {
            relation = dbSession.createEdgeClass(ClassName.E_RELATION.toString());
        }

        closeSession();
    }

    public void openSession() {
        orientDB = new OrientDB(DB_LEVEL, OrientDBConfig.defaultConfig());
        dbSession = orientDB.open(configuration.getDbName(), configuration.getDbUser(), configuration.getDbPass());
    }

    public ODatabaseSession getDbSession() {
        return dbSession;
    }

    public void closeSession() {
        dbSession.close();
        orientDB.close();
    }
}
