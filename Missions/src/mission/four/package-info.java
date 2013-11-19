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
 * <li>{@link mission.four.TreeBuilder}</li>
 * <li>{@link mission.four.Tree}</li>
 * </ul>
 * <li>Loic Lacomblez</li>
 * <ul>
 * <li>{@link mission.four.Tree#put(String, Object)}</li>
 * </ul>
 * <li>Alaaedine Chatri</li>
 * <ul>
 * <li>{@link mission.four.Tree#Tree(int)}</li>
 * <li>{@link mission.four.Tree#get(String)}</li>
 * <li>{@link mission.four.Tree#size()}</li>
 * <li>{@link mission.four.Tree#isEmpty()}</li>
 * </ul>
 * <li>Benoit Sluysmans</li>
 * <ul>
 * <li>{@link mission.four.Tree#getAllKeys(int)}</li>
 * <li>{@link mission.four.Tree#getAllValues(int)}</li>
 * </ul>
 * <li>David Sarkozi</li>
 * <ul>
 * <li>{@link mission.four.Tree#remove(Object)}</li>
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