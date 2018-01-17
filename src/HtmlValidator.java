
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> openTags = new Stack<>();

		while (!tags.isEmpty()) {
			HtmlTag htmlTag = (HtmlTag) tags.poll();
			
			if (htmlTag.isOpenTag()) {
				openTags.push(htmlTag);
				continue;
			}
			
			if (htmlTag.isSelfClosing()) {
				continue;
			}
			
			if (!htmlTag.isOpenTag() && openTags.isEmpty()) {
				openTags = null;
				break;
			}
			
			if (!htmlTag.isOpenTag()) {
				HtmlTag openTag = openTags.peek();
				
				if (openTag.matches(htmlTag)) {
					openTags.pop();
				} else {
					break;
				}
			}
		}
		
		return openTags;
	}
	

}

