package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
      
      jsonIdMap.with(new GroupAccountCreator());
      jsonIdMap.with(new GroupAccountPOCreator());
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new ItemCreator());
      jsonIdMap.with(new ItemPOCreator());
      
      return jsonIdMap;
   }
}
