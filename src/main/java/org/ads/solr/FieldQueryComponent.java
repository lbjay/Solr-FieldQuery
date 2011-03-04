/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ads.solr;

import java.io.IOException;
import java.util.Set;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Query;
import org.apache.solr.core.SolrCore;
import org.apache.solr.core.SolrResourceLoader;
import org.apache.solr.handler.component.QueryComponent;
import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.schema.IndexSchema;
import org.apache.solr.search.SolrIndexSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jluker
 */
public class FieldQueryComponent extends QueryComponent {

  public static final Logger log = LoggerFactory.getLogger(SolrResourceLoader.class);

  @Override
  public void process(ResponseBuilder rb) throws IOException
  {
    SolrQueryRequest req = rb.req;
    SolrQueryResponse rsp = rb.rsp;
    SolrIndexSearcher searcher = req.getSearcher();
    IndexReader reader = searcher.getReader();

    Set<String> returnFields = rsp.getReturnFields();

    SolrCore core = req.getCore();
    IndexSchema schema = core.getSchema();

    FieldCollector collector = new FieldCollector(schema, returnFields);

    SolrIndexSearcher.QueryCommand cmd = rb.getQueryCommand();
    Query query = cmd.getQuery();

    searcher.search(query, collector);
    collector.addValuesToResponse(rsp);
   }
}
