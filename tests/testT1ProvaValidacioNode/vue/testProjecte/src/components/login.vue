<template>
    <form action="/user" method="post">
        <h2>Login</h2>
        <div class="input-field">
            <input type="text" name="username" id="username" placeholder="Enter Username">
        </div>
        <div class="input-field">
            <input type="password" name="password" id="password" placeholder="Enter Password">
        </div>
        <input type="submit" value="LogIn">
    </form>
</template>
<style>

form {
    display: flex;
    flex-direction: column;
}

.input-field {
    position: relative;
    margin-top: 2rem;
}

.input-field input {
    padding: 0.8rem;
}

form .input-field:first-child {
    margin-bottom: 1.5rem;
}

form input[type="submit"] {
    background: linear-gradient(to left, #4776E6, #8e54e9);
    color: white;
    border-radius: 4px;
    margin-top: 2rem;
    padding: 0.4rem;
}

</style>
<script>

</script>