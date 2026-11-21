package com.clovix.config;
import com.clovix.Clovix;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/** Local JSON storage with backup and atomic replacement. */
public final class ConfigStore {
 private static final Gson GSON=new GsonBuilder().setPrettyPrinting().create();
 private final Path file,backup; private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock(); private JsonObject data=new JsonObject();
 public ConfigStore(Path file){this.file=file;this.backup=file.resolveSibling(file.getFileName()+".bak");}
 public void load(){lock.writeLock().lock();try{
  if(!Files.exists(file)){data=new JsonObject();saveLocked();return;}
  try{JsonObject parsed=GSON.fromJson(Files.readString(file,StandardCharsets.UTF_8),JsonObject.class);data=parsed==null?new JsonObject():parsed;}
  catch(Exception broken){Clovix.LOGGER.error("Config is unreadable; attempting backup",broken);if(Files.exists(backup)){JsonObject parsed=GSON.fromJson(Files.readString(backup,StandardCharsets.UTF_8),JsonObject.class);data=parsed==null?new JsonObject():parsed;}else data=new JsonObject();}
 }catch(IOException ex){Clovix.LOGGER.error("Failed to load config",ex);}finally{lock.writeLock().unlock();}}
 public JsonObject snapshot(){lock.readLock().lock();try{return data.deepCopy();}finally{lock.readLock().unlock();}}
 public void put(String key,com.google.gson.JsonElement value){lock.writeLock().lock();try{data.add(key,value);saveLocked();}finally{lock.writeLock().unlock();}}
 public void save(){lock.writeLock().lock();try{saveLocked();}finally{lock.writeLock().unlock();}}
 private void saveLocked(){try{Files.createDirectories(file.getParent());if(Files.exists(file))Files.copy(file,backup,StandardCopyOption.REPLACE_EXISTING);Path tmp=file.resolveSibling(file.getFileName()+".tmp");Files.writeString(tmp,GSON.toJson(data),StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.WRITE);try{Files.move(tmp,file,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.ATOMIC_MOVE);}catch(AtomicMoveNotSupportedException ignored){Files.move(tmp,file,StandardCopyOption.REPLACE_EXISTING);}}catch(IOException ex){Clovix.LOGGER.error("Failed to save config",ex);}}
}
