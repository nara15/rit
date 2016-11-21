
package model.indexer;

import model.dataset.AbstractDataset;

/**
 *
 * @author Jose Mario Naranjo Leiva
 */
public interface IDataSetIndexer<A>
{

    /**
     * This creates an index iterating over a dataset which extracts the
     * information from the files. 
     * @param pCollection The data set to extract the information.
     * @return The number of documents index.
     */
    public int createIndex(AbstractDataset<A> pCollection);
}
