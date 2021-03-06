package org.sdmlib.test.examples.helloworld;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class HelloWorldTmp
{
   @Test
   public void testTmp()
   {
      ClassModel model = new ClassModel("org.sdmlib.test.examples.helloworld.model");

      Clazz nodeClazz = model.createClazz("Node");

      nodeClazz.withBidirectional(nodeClazz, "copy", Cardinality.ONE, "orig", Cardinality.ONE);

      model.generate("src/test/java");

   }
}
