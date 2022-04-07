package publicBus;

public class tstNodes {
	
		public char data;
		public boolean isEnd;
		public tstNodes prev;
		public tstNodes current;
		public tstNodes next;

		
		public tstNodes(char character) {
			this.data = character;
		}
}
