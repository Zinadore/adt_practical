package model;

import java.io.File;
import java.io.IOException;

import Collections.ListInterface;

public interface ISerializer<T> {
	public void serialize(ListInterface<T> serializable, File file) throws IOException;
	public ListInterface<T> deserialize(File file) throws IOException, ClassNotFoundException ;
}
