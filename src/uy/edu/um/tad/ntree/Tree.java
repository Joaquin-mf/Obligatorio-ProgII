/**
 * 
 */
package uy.edu.um.tad.ntree;

/**
 * @author danielpereda
 *
 */
public interface Tree<T> {

	void add(T oValueToAdd, T oParentValue);

	T getValue();

	void setValue(T oValue);

	Tree<T>[] getChilds();

	Tree<T> searchValue(T oValueToSearch);
}
