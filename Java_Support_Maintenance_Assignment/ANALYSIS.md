# Task 2 - ConcurrentModificationException Analysis

## 1. What is the exact cause of ConcurrentModificationException in Java?

ConcurrentModificationException occurs when a collection is structurally modified while it is being iterated using an Iterator or an enhanced for-loop, except through the Iterator's own remove() method. The iterator detects that the collection has been modified and throws the exception.

---

## 2. What code pattern at line 142 most likely triggered this error?

The most likely cause is modifying an ArrayList while iterating over it.

Example:

```java
for (Transaction transaction : transactions) {
    if (transaction.isInvalid()) {
        transactions.remove(transaction);
    }
}
```

Removing an element directly from the collection during iteration causes ConcurrentModificationException.

---

## 3. Provide the minimal code change (one or two lines) that resolves this safely.

Use an Iterator and remove elements through the iterator instead of the collection.

```java
Iterator<Transaction> iterator = transactions.iterator();
while (iterator.hasNext()) {
    Transaction transaction = iterator.next();
    if (transaction.isInvalid()) {
        iterator.remove();
    }
}
```

Using `iterator.remove()` safely removes the current element without causing ConcurrentModificationException.