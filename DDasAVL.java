
public class DDasAVL<K extends Comparable<? super K>, D> implements DD<K, D> {
	private AVLtree<K, D> root;

	public DDasAVL() {
		root = null;
	} // Constructor

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(K k, D d) {
		root = insert(root, k, d);
	}

	public D find(K k) {
		return find(root, k);
	}

	public void remove(K k) {
		root = remove(root, k);
	}

	public int height() {
		return height(root);
	}

	private AVLtree<K, D> insert(AVLtree<K, D> t, K k, D d) {
		if (t == null)
			return new AVLtree<K, D>(k, d, null, null);
		if (k.compareTo(t.key) < 0)
			t.left = insert(t.left, k, d);
		else if (k.compareTo(t.key) > 0)
			t.right = insert(t.right, k, d);
		else
			;
		return balance(t);
	}

	private D find(AVLtree<K, D> t, K k) {
		if (t == null)
			return null;
		if (k.compareTo(t.key) == 0) {
			if (t.isValid == false)
				return null;
			return t.data;
		}
		if (k.compareTo(t.key) < 0)
			return find(t.left, k);
		return find(t.right, k);
	}

	//
	private AVLtree<K, D> findMin(AVLtree<K, D> t) {
		if (t.left == null || !t.left.isValid)
			return t;
		return findMin(t.left);
	}

	private AVLtree<K, D> remove(AVLtree<K, D> t, K k) {
		if (t == null)
			return null;
		int compareResult = k.compareTo(t.key);
		if (compareResult < 0)
			t.left = remove(t.left, k);
		else if (compareResult > 0)
			t.right = remove(t.right, k);
		else {// found
			t.Delete();
		}
		return t;
	}

	private int height(AVLtree<K, D> t) {
		return (t == null) ? -1 : t.height;
	}

	// public AVLtree<K, D> getRoot() {
	// return root;
	// } getRoot method used for testing

	private AVLtree<K, D> balance(AVLtree<K, D> t) {
		if (t == null)
			return t;
		if (height(t.left) - height(t.right) > 1)
			if (height(t.left.left) >= height(t.left.right))
				t = rotateLL(t); // do an LL rotation
			else
				t = rotateLR(t); // do an LR rotation
		else if (height(t.right) - height(t.left) > 1)
			if (height(t.right.right) >= height(t.right.left))
				t = rotateRR(t); // do an RR rotation
			else
				t = rotateRL(t); // do an RL rotation

		t.height = 1 + Math.max(height(t.left), height(t.right));
		return t;
	}

	private AVLtree<K, D> rotateLL(AVLtree<K, D> k2) {
		AVLtree<K, D> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		return k1;
	} // Complete this

	private AVLtree<K, D> rotateLR(AVLtree<K, D> k2) {
		k2.left = rotateRR(k2.left);
		return rotateLL(k2);
	} // Complete this
		// x > y ? x : y

	private AVLtree<K, D> rotateRR(AVLtree<K, D> k2) {
		AVLtree<K, D> k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		return k1;
	} // Complete this

	private AVLtree<K, D> rotateRL(AVLtree<K, D> k2) {
		k2.right = rotateLL(k2.right);
		return rotateRR(k2);
	} // Complete this

}