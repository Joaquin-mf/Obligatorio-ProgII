/**
 * 
 */
package uy.edu.um.tad.ntree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danielpereda
 *
 */
public class TreeImpl<T> implements Tree<T> {

	private T value;
	
	private List<Tree<T>> childs;
	
	/**
	 * Constructor
	 * @param oValue
	 */
	public TreeImpl(T oValue) {
		value = oValue;
		childs = new ArrayList<Tree<T>>();
	}
	
	@Override
	public void add(T oValueToAdd, T oParentValue) {
		if (oValueToAdd == null || oParentValue == null) {
			
			throw new RuntimeException("Invalid value or parent");
		}
		
		if (searchValue(oValueToAdd) != null) {
			
			throw new RuntimeException("Element already exists");
		}
		
		Tree<T> oParent = searchValue(oParentValue);
		
		if (oParent == null) {
			
			throw new RuntimeException("Parent not exists");
			
		}
		
		((TreeImpl<T>) oParent).childs.add(new TreeImpl<T>(oValueToAdd));
		
	}

	public Tree<T> searchValue(T oValueToSearch) {
		Tree<T> oTreeValue = null;
		
		if (oValueToSearch != null) {
			
			if (oValueToSearch.equals(getValue())) {
				
				oTreeValue = this;
				
			} else {
				int nChild = 0;
				
				while (oTreeValue == null && nChild < getChilds().length) {
					
					oTreeValue = getChilds()[nChild].searchValue(oValueToSearch);
					
					nChild++;
				}
				
			}
		}
		
		return oTreeValue;
	}
	
	@Override
	public T getValue() {
		return value;
	}

	@Override
	public Tree<T>[] getChilds() {
		
		Tree<T>[] oVector = new Tree[childs.size()];
		int i = 0;
		
		for (Tree<T> oTemp : childs) {
			
			oVector[i] = oTemp;
			
			i++;
		}
		
		return oVector;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
