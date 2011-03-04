/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ads.solr;

import java.util.ArrayList;
import org.apache.lucene.search.Collector;
import org.apache.solr.response.SolrQueryResponse;

/**
 *
 * @author jluker
 */
public abstract class FieldCollectorBase extends Collector {
    public abstract void addValuesToResponse(SolrQueryResponse rsp);
}
