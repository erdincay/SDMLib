package org.sdmlib.simple.model.attribute_j.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new SimpleSetCreator());
      jsonIdMap.with(new SimpleSetPOCreator());
      return jsonIdMap;
   }
}
