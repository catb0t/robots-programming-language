package app;

import java.util.*;

public class RobotList<E> extends LinkedList<E> {

	Class<E> clazz;

	public RobotList(Class<E> cls) {
		super();
		clazz = cls;
	}

	public E get(float index) {

		E item = null;
		int indexZeroBased = ((int) index) - 1;

		if (indexZeroBased < 0) {
			Global.outputArea.setText("You cannot access the oth or below element of a list\n\n".concat(Global.outputArea.getText()));
		}
		try {

			item = super.get(indexZeroBased);

		} catch (IndexOutOfBoundsException e) {

			try {

				if (clazz.equals(Float.class))
				{
					for (int i = this.size(); i <= indexZeroBased; i++) {
						
						item = (E) new Float(0f);
						this.add(item);
					}
				}
				else if (clazz.equals(Boolean.class)) {
					for (int i = this.size(); i <= indexZeroBased; i++) {

						item = (E) new Boolean(false);
						this.add(item);
					}
				}
				else {
					for (int i = this.size(); i <= indexZeroBased; i++) {

						item = clazz.newInstance();
						this.add(item);
					}
				}

			} catch (InstantiationException e2) {
				Global.outputArea.setText(e2.getMessage().concat("\n\n").concat(Global.outputArea.getText()));
				e2.printStackTrace();
			} catch (IllegalAccessException e2) {
				Global.outputArea.setText(e2.getMessage().concat("\n\n").concat(Global.outputArea.getText()));
				e2.printStackTrace();
			}

		} catch (Exception other) {
			Global.outputArea.setText(other.getMessage().concat("\n\n").concat(Global.outputArea.getText()));
			other.printStackTrace();
		}

		return item;
	}


	public void add (float index, E item) {
		int indexZeroBased = ((int) index) - 1;

		try {

			super.add(indexZeroBased, item);

		} catch (IndexOutOfBoundsException e) {

			try {

				if (clazz.equals(Float.class))
				{
					for (int i = this.size(); i < indexZeroBased; i++) {
						
						E itemT = (E) new Float(0f);
						this.add(itemT);
					}
				}
				else if (clazz.equals(Boolean.class)) {
					for (int i = this.size(); i < indexZeroBased; i++) {

						E itemT = (E) new Boolean(false);
						this.add(itemT);
					}
				}
				else {
					for (int i = this.size(); i < indexZeroBased; i++) {

						E itemT = clazz.newInstance();
						this.add(itemT);
					}
				}
				
				this.addLast(item);

			} catch (InstantiationException e2) {
				e2.printStackTrace();
			} catch (IllegalAccessException e2) {
				e2.printStackTrace();
			}

		} catch (Exception other) {
			other.printStackTrace();
		}
	}


	public void remove (float index) {

		int indexZeroBased = ((int) index) - 1;
		if (indexZeroBased < this.size()) {

			this.remove(indexZeroBased);
		}
	}


	public void set (float index, E e) {
		int indexZeroBased = ((int) index) - 1;
		if (indexZeroBased < this.size()) {
			this.set(indexZeroBased, e);
		}
		else {
			this.add(index,e);
		}
	}


}
