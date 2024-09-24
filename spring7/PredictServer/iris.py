# pakage import
import pandas as pd
import pickle

from sklearn.datasets import load_iris
from sklearn.neighbors import KNeighborsClassifier      ## 분류 분석
from sklearn.model_selection import train_test_split

# 데이터 불러오기
iris = load_iris()

# print(iris)

# DataFrame로 만들기
iris_df = pd.DataFrame(iris['data'], columns=iris['feature_names'])
target = iris['target_names']

# print(iris_df)
# print(target)

# x와 y 처리
X = iris_df
y = iris['target']


# 섞어서 나누는 작업
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=1)

# 학습
knn_model = KNeighborsClassifier(n_neighbors=3)
knn_model.fit(X_train, y_train)

# 예측
knn_model.predict(X_test)

# Score 보기
print(knn_model.score(X_test, y_test))

# 가져온 데이터 1개 예측
pred = knn_model.predict([[5.1, 3.5, 1.4, 0.2]])   # 0번 데이터 (세토사)
print('예측 결과: ', target[pred[0]])

# 피클로 저장하기
with open('data.pickle', 'wb') as f:
    pickle.dump(knn_model, f, pickle.HIGHEST_PROTOCOL)

print("저장 완료!")