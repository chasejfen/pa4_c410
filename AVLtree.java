
public class AVLtree<K extends Comparable<? super K>, D> {
	K key;
	D data;
	boolean isValid = true; // isvalid will be set to false when delete is
							// called.
	AVLtree<K, D> left, right;
	public int height;

	public AVLtree(K k, D d, AVLtree<K, D> l, AVLtree<K, D> r) {
		key = k;
		data = d;
		left = l;
		right = r;
		height = 0;
		isValid = true;
	}

	public void Delete() {
		isValid = false;
	}
}
