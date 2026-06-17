class Actor:
    def __init__(self, name: str):
        self.name = name
        self.page = None

    def who_can_browse_with(self, page):
        self.page = page
        return self

    def attempts_to(self, *tasks):
        for task in tasks:
            task.perform_as(self)

    def was_able_to(self, *tasks):
        self.attempts_to(*tasks)

    def asks_for(self, question):
        return question.answered_by(self)
