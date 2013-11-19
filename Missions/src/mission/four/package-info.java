/**
 * Mission 4 - Implementation d'un arbre binaire de recherche permettant
 * d'ordonner des revues scientifiques a partir d'un fichier d'entree, et
 * d'effectuer des operations de recherche, comparaison, insertion et
 * suppression.
 * 
 * Repartition des taches :
 * <ul>
 * <li>Clementine Munyabarenzi</li>
 * <ul>
 * <li>{@link TreeBuilder}</li>
 * <li>{@link mission.four.Tree}</li>
 * </ul>
 * <li>Loic Lacomblez</li>
 * <ul>
 * <li>{@link Tree#put(String, Journal)}</li>
 * </ul>
 * <li>Alaaedine Chatri</li>
 * <ul>
 * <li>{@link Tree#Tree(int)}</li>
 * <li>{@link Tree#get(String)}</li>
 * <li>{@link Tree#size()}</li>
 * <li>{@link Tree#isEmpty()}</li>
 * </ul>
 * <li>Benoit Sluysmans</li>
 * <ul>
 * <li>{@link Tree#getAllKeys(int)}</li>
 * <li>{@link Tree#getAllValues(int)}</li>
 * <li>{@link Tree#getAllKeysWithRank(String)}</li>
 * </ul>
 * <li>David Sarkozi</li>
 * <ul>
 * <li>{@link Tree#remove(Object)}</li>
 * <li>{@link mission.four.Dictionary}</li>
 * <li>{@link mission.four.FileManager}</li>
 * </ul>
 * <li>Henri Crombe</li>
 * <ul>
 * <li>Responsable rapport</li>
 * </ul>
 * <li>Julien De Maeyer</li>
 * <ul>
 * <li>Responsable questions 10 et 11</li>
 * </ul>
 * <li>Nicolas Marchand</li>
 * <ul>
 * <li>Responsable question 9</li>
 * </ul>
 * </ul>
 * 
 * @author Groupe 11
 * 
 */
package mission.four;