package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Collections.ListInterface;
@SuppressWarnings("unchecked")
public class Serializer<T> implements ISerializer<T> {

	
	@Override
	public void serialize(ListInterface<T> serializable, File file) throws IOException{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(serializable);
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public ListInterface<T> deserialize(File file) throws IOException, ClassNotFoundException {
		try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))){
			return ((ListInterface<T>)is.readObject());
	    } catch (IOException e) {
	    	throw e;
	    } catch (ClassNotFoundException e) {
			throw e;
		}
	}

}
