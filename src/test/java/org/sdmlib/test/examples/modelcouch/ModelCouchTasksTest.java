package org.sdmlib.test.examples.modelcouch;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ContentHandler;
import java.net.ContentHandlerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assume.*;

import org.sdmlib.modelcouch.CouchDBAdapter;
import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.modelcouch.ModelCouch.ApplicationType;
import org.sdmlib.modelcouch.RequestObject;
import org.sdmlib.modelcouch.RequestType;
import org.sdmlib.modelcouch.ReturnObject;
import org.sdmlib.modelcouch.authentication.CookieAuthenticator;
import org.sdmlib.storyboards.StoryPage;
import org.sdmlib.test.examples.modelcouch.util.PersonCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.list.SimpleList;

public class ModelCouchTasksTest {
	private static final int DB_PORT = 5984;
	private static final String DB_USERNAME = "segroup";
	private static final String DB_PASSWORD = "";
	private static final String DB_HOST = "docker.cs.uni-kassel.de";
	private static final String DB_NAME = "segroup";

	/**
	 * 
	 * @see <a href='../../../../../../../../doc/BasicModelOnTheCouch.html'>
	 *      BasicModelOnTheCouch.html</a>
	 * @see <a href='../../../../../../../../doc/BasicModelOnTheCouch.html'>
	 *      BasicModelOnTheCouch.html</a>
	 * @see <a href='../../../../../../../../doc/BasicModelOnTheCouch.html'>
	 *      BasicModelOnTheCouch.html</a>
	 * @see <a href='../../../../../../../../doc/BasicModelOnTheCouch.html'>BasicModelOnTheCouch.html</a>
 */
	@Test
	public void testBasicModelOnTheCouch() {
		CouchDBAdapter adapter = createAdapter();
		StoryPage story = new StoryPage();

		story.add(""
				+ "This is a test for writing objects to the CouchDB instance https://docker.cs.uni-kassel.de:5984/ and read from it. "
				+ "First a database is created, the following objects get persisted and the connection gets closed.");

		String databaseName = DB_NAME;

		String sessionid = "testBasicModelOnTheCouch" + System.currentTimeMillis();

		IdMap idMap = PersonCreator.createIdMap(sessionid);

		ModelCouch couch = createCouch(adapter);
		couch.withApplicationType(ApplicationType.StandAlone).withIdMap(idMap).registerAtIdMap();

		couch.open(databaseName);

		if (couch.getModelDBListener() == null) {
			// no database.
			// abort test
			// System.out.println("Could not connect to " +
			// couch.getHostName());
			assumeTrue("Couldn't connect to CouchDB!", False());
			return;
		}

		Person seGroup = new Person();
		idMap.put("root", seGroup);

		DocumentData seGroupData = seGroup.createPersonData().withValue("segroup");

		seGroupData.withType("list of person").withLastEditor("zuendorf")
				.withLastModified("29.12.2015 19:18:42:123 CEST");

		Person tobi = seGroup.createMembers();
		DocumentData tobiData = tobi.createPersonData().withType("person").withValue("tobi")
				.withLastModified("29.12.2015 20:05:23:123 CEST");

		DocumentData tobiLogin = tobiData.createSubData().withTag("login").withValue("lowrider").withType("login")
				.withLastEditor("tobi").withLastModified("29.12.2015 20:05:23:124 CEST");

		Task tobiTask = tobi.createTasks();
		DocumentData tobiTaskData = tobiTask.createTaskData().withTag("test model replication on CouchDB")
				.withType("task").withPersons((Person) tobi);

		couch.close();

		story.addObjectDiagram(seGroup);

		story.add(""
				+ "Afterwards the objects get reconstructed by reading all persisted documents from the same CouchDB instance.");

		String resultSessionid = "resultBasicModelOnTheCouch" + System.currentTimeMillis();
		IdMap resultiIdMap = PersonCreator.createIdMap(resultSessionid);

		couch = createCouch(adapter).withIdMap(resultiIdMap).registerAtIdMap();

		// long startMillis = System.currentTimeMillis();
		// while (System.currentTimeMillis() < startMillis + 5000) {
		// // wait 3 seconds
		// }
		couch.open(databaseName);

		Person resultSeGroup = (Person) resultiIdMap.getObject("root");

		story.addObjectDiagram(resultSeGroup);

		assertEquals("person", resultSeGroup.getMembers().first().getPersonData().first().getType());
		assertEquals("tobi", resultSeGroup.getMembers().first().getPersonData().first().getValue());

		story.add("" + "Finally the database is deleted.");

		couch.getCouchDBAdapter().deleteDatabase(databaseName);

		story.dumpHTML();
	}

	public ModelCouch createCouch(CouchDBAdapter couchDBAdapter) {
		ModelCouch couch = new ModelCouch(couchDBAdapter).withApplicationType(ApplicationType.StandAlone)
				.withContinuous(true);

		return couch;
	}

	public CouchDBAdapter createAdapter() {
		CouchDBAdapter couchDBAdapter = new CouchDBAdapter().withHostName(DB_HOST).withPort(DB_PORT)
				.withUserName(DB_USERNAME);
		if (DB_PASSWORD != null && !DB_PASSWORD.equals("")) {
			try {
				couchDBAdapter.withAuthenticator(new CookieAuthenticator()).login(DB_PASSWORD);
			} catch (Exception e) {
				assumeTrue("Couldn't login into DB", False());
			}
		}
		return couchDBAdapter;
	}

	@After
	public void cleanup() {
		CouchDBAdapter adapter = createAdapter();

		RequestObject delete = adapter.createRequestObject();
		delete.setRequestType(RequestType.DELETE);
		delete.setPath(DB_NAME);
		adapter.send(delete);
		delete.setPath(DB_NAME + "-replicate");
		adapter.send(delete);
	}

	@BeforeClass
	public static void testForConnection() {
		CouchDBAdapter couchDBAdapter = new CouchDBAdapter().withUserName(DB_USERNAME);
		assumeTrue("Couch is not Reachable", couchDBAdapter.testConnection());

		if (DB_PASSWORD != null && !DB_PASSWORD.equals("")) {
			try {
				if (couchDBAdapter.withAuthenticator(new CookieAuthenticator()).login(DB_PASSWORD) == null) {
					assumeTrue("Couldn't login into DB", False());
				}
			} catch (Exception e) {
				assumeTrue("Couldn't login into DB", False());
			}
		}

		ModelCouch couch = new ModelCouch(couchDBAdapter).withHostName(DB_HOST).withPort(DB_PORT)
				.withUserName(DB_USERNAME).withApplicationType(ApplicationType.StandAlone);
		try {
			couch.open(DB_NAME);
		} catch (Exception e) {
			assumeTrue("Couldn't establish Connection to DB", False());
		}

		if (couch.getModelDBListener() == null) {
			// no database.
			// abort test
			// System.out.println("Could not connect to " +
			// couch.getHostName());
			assumeTrue(False());
			return;
		}
		couch.close();
		couchDBAdapter.deleteDatabase(DB_NAME);
	}

	@Test
	public void testUserPrivileges() {
		CouchDBAdapter adapter = createAdapter();

		// first create temporary DB
		adapter.createDB(DB_NAME);

		ReturnObject setUserPrivileges = adapter.setUserPrivileges(DB_NAME, null,
				Arrays.asList("admin"), null, Arrays.asList("user"));
		assertEquals(200, setUserPrivileges.getResponseCode());
		LinkedList<String> content = setUserPrivileges.getContent();
		if (content == null || content.size() == 0) {
			System.out.println("NULL!");
		}
		SimpleList<Object> response = new JsonArray().withValue(content.toString());
		JsonObject ok = (JsonObject) response.get(0);
		assertEquals(true, ok.get("ok"));
	}

	@Test
	public void testReplication() {
		CouchDBAdapter adapter = createAdapter();
		String databaseName = DB_NAME;

		String sessionid = "testReplication" + System.currentTimeMillis();
		IdMap idMap = PersonCreator.createIdMap(sessionid);

		ModelCouch couch = createCouch(adapter);
		couch.withApplicationType(ApplicationType.StandAlone).withIdMap(idMap).registerAtIdMap();

		couch.open(databaseName);

		if (couch.getModelDBListener() == null) {
			// no database.
			// abort test
			// System.out.println("Could not connect to " +
			// couch.getHostName());
			assumeTrue("Couldn't connect to CouchDB!", False());
			return;
		}

		Person seGroup = new Person();
		idMap.put("root", seGroup);

		DocumentData seGroupData = seGroup.createPersonData().withValue("segroup");

		seGroupData.withType("list of person").withLastEditor("zuendorf")
				.withLastModified("29.12.2015 19:18:42:123 CEST");

		Person tobi = seGroup.createMembers();
		DocumentData tobiData = tobi.createPersonData().withType("person").withValue("tobi")
				.withLastModified("29.12.2015 20:05:23:123 CEST");

		DocumentData tobiLogin = tobiData.createSubData().withTag("login").withValue("lowrider").withType("login")
				.withLastEditor("tobi").withLastModified("29.12.2015 20:05:23:124 CEST");

		Task tobiTask = tobi.createTasks();
		DocumentData tobiTaskData = tobiTask.createTaskData().withTag("test model replication on CouchDB")
				.withType("task").withPersons((Person) tobi);

		ReturnObject replicate = couch.getCouchDBAdapter().replicate(couch, couch.getURL().toString(),
				DB_NAME + "-replicate");

		if (replicate.getError() != null && replicate.getError().size() > 0) {
			System.out.println(replicate.getError().toString());
		}

		couch.close();
		// long startMillis = System.currentTimeMillis();
		// while (System.currentTimeMillis() < startMillis + 5000) {
		// // wait 3 seconds
		// }

		String resultSessionid = "resultBasicModelOnTheCouch" + System.currentTimeMillis();
		IdMap resultiIdMap = PersonCreator.createIdMap(resultSessionid);

		couch = createCouch(adapter).withIdMap(resultiIdMap).registerAtIdMap();

		couch.open(databaseName + "-replicate");

		Person resultSeGroup = (Person) resultiIdMap.getObject("root");
		assertEquals("person", resultSeGroup.getMembers().first().getPersonData().first().getType());
		assertEquals("tobi", resultSeGroup.getMembers().first().getPersonData().first().getValue());

		couch.getCouchDBAdapter().deleteDatabase(databaseName);
	}

	@Test
	public void testSendAndConnection() {
		/**
		 * {"couchdb":"Welcome","uuid":"40b8d651b68043e5195153dfcd218f1f",
		 * "version":"1.6.1","vendor":{"version":"1.6.1","name":
		 * "The Apache Software Foundation"}}
		 */
		CouchDBAdapter adapter = createAdapter();
		RequestObject request = adapter.createRequestObject();
		request.setRequestType(RequestType.GET);
		request.setShouldHandleInput(true);

		ReturnObject send = adapter.send(request);
		JsonObject returnValue = new JsonObject().withValue(send.getContent().getFirst());
		assertEquals("Welcome", returnValue.get("couchdb"));
	}

	@Test
	public void testDatabaseReachable() {
		// ModelCouch couch = createCouch();
		CouchDBAdapter couchDBAdapter = new CouchDBAdapter();
		assertFalse(couchDBAdapter.testConnection(DB_NAME));
		couchDBAdapter.createDB(DB_NAME);
		assertTrue(couchDBAdapter.testConnection(DB_NAME));
	}

	private static boolean False() {
		return false;
	}

}
