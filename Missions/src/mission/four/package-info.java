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
 * <li>{@link TreeNode}</li>
 * </ul>
 * <li>Loic Lacomblez</li>
 * <ul>
 * <li>{@link Tree#put(String, Journal)}</li>
 * </ul>
 * <li>Alaaedine Chatri</li>
 * <ul>
 * <li>{@link Tree#Tree()}</li>
 * <li>{@link Tree#get(String)}</li>
 * <li>{@link Tree#size()}</li>
 * <li>{@link Tree#isEmpty()}</li>
 * </ul>
 * <li>Benoit Sluysmans</li>
 * <ul>
 * <li>{@link Tree#keySet()}</li>
 * <li>{@link Tree#entrySet()}</li>
 * <li>{@link Tree#values()}</li>
 * </ul>
 * <li>David Sarkozi</li>
 * <ul>
 * <li>{@link Tree#remove(String)}</li>
 * <li>{@link Dictionary}</li>
 * <li>{@link mission.four.FileManager}</li>
 * </ul>
 * <li>Henri Crombe</li>
 * <ul>
 * <li>Responsable rapport et analyse de la complexite</li>
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
 * 
 * @author Groupe 11
 * 
 * @throws NullPointerException
 *             Si un ou plusieurs arguments des methodes implementees sont null.
 *             Comportement par defaut, sauf si la specification permet ce type
 *             d'argument.
 * 
 */
package mission.four;