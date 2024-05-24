import org.junit.Before;
import org.junit.Test;
import uy.edu.um.tad.ntree.Tree;
import uy.edu.um.tad.ntree.TreeImpl;
import static org.junit.Assert.*;

public class nTreeTest { //add,getValue,setValue,getChilds,searchValue
    Tree<Integer> sauce ;
    @Before
    public void base(){
        sauce = new TreeImpl<>(23);
        sauce.add(2,23);
        sauce.add(84,23);
        sauce.add(7,23);
        sauce.add(42, 2);
        sauce.add(51, 2);
        sauce.add(67, 2);
        sauce.add(74, 7);
    }

    @Test
    public void TestAddGetValue(){
        assertEquals(Integer.valueOf(23),sauce.getValue()); //pruebo el add usando el getValue y vice-versa
    }
    @Test
    public void TestSetValue(){
        sauce.setValue(4);
        assertEquals(Integer.valueOf(4),sauce.getValue());
    }
    @Test
    public void TestGetChilds(){
        Integer[] SauceChildsManual = {2,84,7};
        Integer[] SauceChilds = new Integer[sauce.getChilds().length];
        for (int i = 0; i < sauce.getChilds().length; i++) {
            SauceChilds[i] = sauce.getChilds()[i].getValue();
        }
        assertArrayEquals(SauceChildsManual,SauceChilds);
    }

    @Test
    public void TestSearchValue(){
        assertNotNull(sauce.searchValue(84));
        assertEquals(Integer.valueOf(84),sauce.searchValue(84).getValue());
    }

}
