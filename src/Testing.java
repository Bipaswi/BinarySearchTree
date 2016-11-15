import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class Testing {

	// elements to add
	BinarySearchTree a = makeTree(new int[] { 3, 8, 1, 4, 6, 2, 10, 9, 20, 25, 15, 16, 14 });
	BinarySearchTree b = makeTree(new int[] { 3, 8, 10 });
	BinarySearchTree c = makeTree(new int[] { 10, 8, 7, 4, 9 });
	BinarySearchTree d = makeTree(new int[] { 6, 3 });
	BinarySearchTree e = makeTree(new int[] { 5, 8, 7 });
	BinarySearchTree g = makeTree(new int[] {9, 3, });
	BinarySearchTree h = makeTree(new int[] {});
	
	BinarySearchTree f = new BinarySearchTree();
	
	Set s = new Set();

	public static BinarySearchTree makeTree(int[] values) {
		BinarySearchTree b = new BinarySearchTree();

		for (int i = 0; i < values.length; i++) {
			b.add(values[i]);
		}

		return b;
	}
	
	@Test
	public void addAndContainsTest() {

		f.add(3);
		f.add(8);
		f.add(1);
		f.add(4);

		assertTrue(f.contains(3));
		assertTrue(f.contains(8));
		assertTrue(f.contains(1));
		assertTrue(f.contains(4));
		assertFalse(f.contains(100));
		assertFalse(f.contains(9));

	}

	//check again
	@Test
	public void emptyTest(){
		f.remove(5);
		assertFalse(f.contains(5));
	}

	@Test
	public void removeNodeWithNoChildren() {

		a.remove(2);
		a.remove(14);
		assertFalse(a.contains(2));
		assertFalse(a.contains(14));

	}

	@Test
	//remove node with onechildright
	public void removeNodeWithOneChild() {

		a.remove(4);
		
		assertTrue(a.contains(2));
		assertFalse(a.contains(4));
		assertTrue(a.contains(6));

	}

	@Test
	public void removeNodeWithTwoChildren() {

		a.remove(10);
		assertEquals(false, a.contains(10));
		assertTrue(a.contains(6));

	}

	@Test
	public void flattenTest() {
		String expected = "1, 2, 3, 4, 6, 8, 9, 10, 14, 15, 16, 20, 25, ";
		String flattenedList = s.printList(s.flatten(a.root));
		assertEquals(expected, flattenedList);
	}

	@Test
	public void mergeTest() {
		String expected = "3, 5, 6, 7, 8, ";
		ArrayList<Integer> one = s.flatten(e.root);
		ArrayList<Integer> two = s.flatten(d.root);
		ArrayList<Integer> merged = s.merge(one, two);
		
		String flat = s.printList(merged);
		assertEquals(expected, flat);
	}
	
	@Test
	public void intersectionMergeTest() {
		String expected = "8, 10, ";
		ArrayList<Integer> one = s.flatten(b.root);
		ArrayList<Integer> two = s.flatten(c.root);
		ArrayList<Integer> merged = s.intersectionMerge(one, two);
		
		String flat = s.printList(merged);
		assertEquals(expected, flat);
	}
	
	@Test
	public void mergeWithDuplicatesTest(){
		String expected = "3, 5, 7, 8, 10, ";
		ArrayList<Integer> one = s.flatten(b.root);
		ArrayList<Integer> two = s.flatten(e.root);
		ArrayList<Integer> merged = s.merge(one, two);
		
		String flat = s.printList(merged);
		assertEquals(expected, flat);
	}
	
	@Test
	public void unionTest() {
		BinarySearchTree newTree = s.union(e, d);
		
		assertTrue(newTree.contains(3));
		assertTrue(newTree.contains(6));
		assertTrue(newTree.contains(5));
		assertTrue(newTree.contains(7));
		assertTrue(newTree.contains(8));
		assertFalse(newTree.contains(15));
	}
	
	@Test 
	public void intersectionPrintTest(){
		BinarySearchTree newTree = s.intersection(b, c);
		String expected = "8, 10, ";
		String flattenedList = s.printList(s.flatten(newTree.root));
		assertEquals(expected, flattenedList);
	}
	
	@Test
	public void unionTestWithDuplicates(){
		BinarySearchTree newTree = s.union(b, c);
		String expected = "3, 4, 7, 8, 9, 10, ";
		String flattenedList = s.printList(s.flatten(newTree.root));
		assertEquals(expected, flattenedList);
	}
	
	
}