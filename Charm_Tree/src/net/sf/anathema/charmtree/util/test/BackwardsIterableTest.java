package net.sf.anathema.charmtree.util.test;

import java.util.Arrays;
import java.util.List;

import net.sf.anathema.charmtree.util.BackwardsIterable;
import net.sf.anathema.lib.testing.BasicTestCase;

public class BackwardsIterableTest extends BasicTestCase {

  public void testArrayIteration() throws Exception {
    Integer[] array = new Integer[] { 1, 2, 3 };
    for (int integer : new BackwardsIterable<Integer>(array)) {
      assertEquals(3, integer);
      return;
    }
  }

  public void testListIteration() throws Exception {
    List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3 });
    for (int integer : new BackwardsIterable<Integer>(list)) {
      assertEquals(3, integer);
      return;
    }
  }
}