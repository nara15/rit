package model.indexer;

import java.io.FileFilter;
import java.io.IOException;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public interface ISimpleIndexer
{
    public int createIndex(String pDataDirPath, FileFilter pFilter) throws IOException;
}
