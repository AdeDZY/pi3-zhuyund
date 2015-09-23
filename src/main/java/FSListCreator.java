import java.util.Collection;
import java.util.Iterator;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;

import type.ComponentAnnotation;
/**
 * Used for creating FSList from java Collection
 * @author zhuyund
 *
 */
public class FSListCreator {
	 public static FSList createFSList(JCas aJCas, Collection<ComponentAnnotation> aCollection)
     {
             if (aCollection.size() == 0) {
                     return new EmptyFSList(aJCas);
             }

             NonEmptyFSList head = new NonEmptyFSList(aJCas);
             NonEmptyFSList list = head;
             Iterator<ComponentAnnotation> i = aCollection.iterator();
             while (i.hasNext()) {
                     head.setHead(i.next());
                     if (i.hasNext()) {
                             head.setTail(new NonEmptyFSList(aJCas));
                             head = (NonEmptyFSList) head.getTail();
                     }
                     else {
                             head.setTail(new EmptyFSList(aJCas));
                     }
             }

             return list;
     }
}
