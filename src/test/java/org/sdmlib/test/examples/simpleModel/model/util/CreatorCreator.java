package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.BigBrotherCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.BigBrotherPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.ObjectCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.ObjectPOCreator());

      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.MacListCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.MacListPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.PersonPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.ItemCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.simpleModel.model.util.ItemPOCreator());
      return jsonIdMap;
   }
}